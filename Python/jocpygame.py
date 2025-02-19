import pygame
import random
import sys

from pygame.examples.midi import fill_region

# ========================
# Configuració inicial
# ========================
# Get the current display's resolution
FPS = 60

# Colors (RGB)
WHITE = (255, 255, 255)
BLACK = (0, 0, 0)
RED   = (255, 0, 0)
BLUE  = (0, 0, 255)

# Inicialitzar Pygame i la finestra
pygame.init()
display_info = pygame.display.Info()
WIDTH, HEIGHT = display_info.current_w, display_info.current_h
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Joc Extensible - Ampliació 4: Menú i Reinici")
clock = pygame.time.Clock()
font = pygame.font.SysFont("Arial", 24)

# ========================
# Variables Globals del Joc
# ========================
score = 0
difficulty_level = 1
lives = 3
last_difficulty_update_time = pygame.time.get_ticks()
spawn_interval = 1500
ADD_OBSTACLE = pygame.USEREVENT + 1

# Images
character = "srcs/paperplane.png"
enemies = "srcs/scissors.png"
bullet = "srcs/rock.png"
# ========================
# Funcions Auxiliars
# ========================

def draw_text(surface, text, font, color, x, y):
    """Dibuixa un text a la pantalla."""
    text_obj = font.render(text, True, color)
    surface.blit(text_obj, (x, y))

# ========================
# Classes del Joc
# ========================

class Player(pygame.sprite.Sprite):
    """Classe per al jugador."""
    def __init__(self):
        super().__init__()
        self.image = pygame.image.load(character)
        self.image = pygame.transform.scale(self.image, (50, 50))
        self.rect = self.image.get_rect()
        self.rect.center = (100, HEIGHT // 2)
        self.speed = 5

    def update(self):
        """Actualitza la posició del jugador segons les tecles premudes."""
        keys = pygame.key.get_pressed()
        if keys[pygame.K_UP] or keys[pygame.K_w]:
            self.rect.y -= self.speed
        if keys[pygame.K_DOWN] or keys[pygame.K_s]:
            self.rect.y += self.speed
        if keys[pygame.K_LEFT] or keys[pygame.K_a]:
            self.rect.x -= self.speed
        if keys[pygame.K_RIGHT] or keys[pygame.K_d]:
            self.rect.x += self.speed
        if keys[pygame.K_SPACE]:
            shoot()
        if keys[pygame.K_ESCAPE]:
            show_pause()

        # Evitar que el jugador surti de la pantalla
        if self.rect.left < 0:
            self.rect.left = 0
        if self.rect.right > WIDTH:
            self.rect.right = WIDTH
        if self.rect.top < 0:
            self.rect.top = 0
        if self.rect.bottom > HEIGHT:
            self.rect.bottom = HEIGHT

class Obstacle(pygame.sprite.Sprite):
    """Classe per als obstacles."""
    def __init__(self):
        super().__init__()
        self.image = pygame.image.load(enemies)
        # Agafa les dimensions originals
        original_width, original_height = self.image.get_size()
        # Crear un obstacle amb dimensions aleatòries
        width = random.randint(5, 30)
        height = int(width*original_height/100)
        self.image = pygame.transform.scale(self.image, (width*original_width/100, height))
        self.rect = self.image.get_rect()
        # Posició inicial: fora de la pantalla per la dreta
        self.rect.x = WIDTH + random.randint(10, 100)
        self.rect.y = random.randint(0, HEIGHT - height)
        # La velocitat s'incrementa amb la dificultat
        self.speed = random.randint(3 + difficulty_level, 7 + difficulty_level)

    def update(self):
        """Actualitza la posició de l'obstacle movent-lo cap a l'esquerra.
           Quan surt completament de la pantalla, s'incrementa la puntuació i s'elimina."""
        global score
        self.rect.x -= self.speed
        if self.rect.right < 0:
            score += 1
            self.kill()
class Dispar(pygame.sprite.Sprite):
    """Classe per als dispars."""
    def __init__(self, x, y):
        super().__init__()
        # Crear una bala amb dimensions
        self.image = pygame.image.load(bullet)
        self.image = pygame.transform.scale(self.image, (30, 30))
        self.rect = self.image.get_rect()
        # Posició inicial: fora de la pantalla per la dreta
        self.rect.center = (x, y)
        self.speed = 15

    def update(self):
        """Actualitza la posició de l'obstacle movent-lo cap a l'esquerra.
           Quan surt completament de la pantalla, s'incrementa la puntuació i s'elimina."""
        global score
        self.rect.x += self.speed
        if self.rect.right > WIDTH:
            self.kill()
# ========================
# Funció per reinicialitzar el Joc
# ========================

def new_game():
    """Reinicialitza totes les variables i grups per començar una nova partida."""
    global score, difficulty_level, lives, last_difficulty_update_time, spawn_interval, all_sprites, obstacles, player
    score = 0
    difficulty_level = 1
    lives = 3
    last_difficulty_update_time = pygame.time.get_ticks()
    spawn_interval = 1500
    pygame.time.set_timer(ADD_OBSTACLE, spawn_interval)
    all_sprites = pygame.sprite.Group()
    obstacles = pygame.sprite.Group()
    player = Player()
    all_sprites.add(player)

# ========================
# Funció per mostrar el menú principal
# ========================

def show_menu():
    """Mostra la pantalla de menú d'inici i espera que l'usuari premi alguna tecla per començar."""
    waiting = True
    while waiting:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                waiting = False
        screen.fill(WHITE)
        draw_text(screen, "Joc Extensible", font, BLACK, 300, 200)
        draw_text(screen, "Prem qualsevol tecla per començar", font, BLACK, 220, 250)
        pygame.display.flip()

# ========================
# Funció per executar la partida
# ========================

def game_loop():
    """Executa el bucle principal de la partida."""
    global difficulty_level, last_difficulty_update_time, spawn_interval, lives
    new_game()
    game_state = "playing"
    running = True
    while running and game_state == "playing":
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == ADD_OBSTACLE:
                obstacle = Obstacle()
                all_sprites.add(obstacle)
                obstacles.add(obstacle)
        # Incrementar la dificultat cada 10 segons
        current_time = pygame.time.get_ticks()
        if current_time - last_difficulty_update_time >= 10000:
            difficulty_level += 1
            last_difficulty_update_time = current_time
            spawn_interval = max(500, 1500 - difficulty_level * 100)
            pygame.time.set_timer(ADD_OBSTACLE, spawn_interval)
        # Actualitzar els sprites
        all_sprites.update()
        # Comprovar col·lisions
        if pygame.sprite.spritecollideany(player, obstacles):
            lives -= 1
            if lives > 0:
                # Reinicialitzar la posició del jugador i esborrar els obstacles
                player.rect.center = (100, HEIGHT // 2)
                for obs in obstacles:
                    obs.kill()
            else:
                game_state = "game_over"
        # Dibuixar la escena
        screen.fill(WHITE)
        all_sprites.draw(screen)
        score_text = font.render("Puntuació: " + str(score), True, BLACK)
        difficulty_text = font.render("Dificultat: " + str(difficulty_level), True, BLACK)
        lives_text = font.render("Vides: " + str(lives), True, BLACK)
        screen.blit(score_text, (10, 10))
        screen.blit(difficulty_text, (10, 40))
        screen.blit(lives_text, (10, 70))
        pygame.display.flip()
    return score
# ========================
# Funció per disparar
# ========================

def shoot():
    """Dispara roca."""

# ========================
# Funció per mostrar la pantalla de Game Over
# ========================

def show_game_over(final_score):
    """Mostra la pantalla de Game Over amb la puntuació final i espera per reiniciar."""
    waiting = True
    while waiting:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                waiting = False
        screen.fill(WHITE)
        draw_text(screen, "Game Over!", font, RED, 350, 200)
        draw_text(screen, "Puntuació Final: " + str(final_score), font, BLACK, 320, 250)
        draw_text(screen, "Prem qualsevol tecla per reiniciar", font, BLACK, 250, 300)
        pygame.display.flip()
# ========================
# Funció per mostrar la pantalla de Pausa
# ========================

def show_pause():
    """Mostra la pantalla de Pausa."""
    waiting = True
    while waiting:
        clock.tick(FPS)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                waiting = False
        screen.fill(WHITE)
        draw_text(screen, "Pause", font, RED, 350, 200)
        draw_text(screen, "Prem qualsevol tecla per continuar", font, BLACK, 250, 300)
        pygame.display.flip()
# ========================
# Bucle principal del programa
# ========================

while True:
    show_menu()                   # Mostrar menú d'inici
    final_score = game_loop()       # Executar la partida
    show_game_over(final_score)     # Mostrar pantalla de Game Over i esperar reinici