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
last_shot_time = 0  # Track the time of the last shot
shoot_cooldown = 300  # Cooldown time in milliseconds (0.5 seconds)

# Images
character = "srcs/paperplane.png"
enemies = "srcs/scissors.png"
bullet = "srcs/rock.png"
background_image = pygame.image.load("srcs/background.jpg")
background_image = pygame.transform.scale(background_image, (WIDTH, HEIGHT))
# ========================
# Funcions Auxiliars
# ========================

def draw_text(surface, text, font, color, x, y):
    """Dibuixa un text a la pantalla."""
    text_obj = font.render(text, True, color)
    text_rect = text_obj.get_rect()
    text_rect.center = (x, y)
    surface.blit(text_obj, text_rect)
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
        original_width, original_height = self.image.get_size()
        width = random.randint(5, 30)
        height = int(width * original_height / 100)
        self.image = pygame.transform.scale(self.image, (width * original_width / 100, height))
        self.rect = self.image.get_rect()
        self.rect.x = WIDTH + random.randint(10, 100)
        self.rect.y = random.randint(0, HEIGHT - height)
        self.speed = random.randint(3 + difficulty_level, 7 + difficulty_level)

    def update(self):
        """Actualitza la posició de l'obstacle movent-lo cap a l'esquerra."""
        global score
        self.rect.x -= self.speed
        if self.rect.right < 0:
            score += 1
            self.kill()


class Projectile(pygame.sprite.Sprite):
    """Classe per al projectil (roca)."""
    def __init__(self, x, y, speed=10):
        super().__init__()
        self.image = pygame.image.load(bullet)
        self.image = pygame.transform.scale(self.image, (30, 30))
        self.rect = self.image.get_rect()
        self.rect.center = (x, y)
        self.speed = speed

    def update(self):
        global score
        """Move the projectile and check for collisions with obstacles."""
        self.rect.x += self.speed

        # If the projectile goes off-screen, remove it
        if self.rect.left > WIDTH:
            self.kill()

        # Check for collisions with obstacles
        collided_obstacles = pygame.sprite.spritecollide(self, obstacles, True)
        if collided_obstacles:
            # If collision with an obstacle, kill the projectile and the obstacle
            self.kill()
            for obstacle in collided_obstacles:
                obstacle.kill()
                score += 1  # Optionally increment score for each obstacle hit


# ========================
# Funció per reinicialitzar el Joc
# ========================

def new_game():
    """Reinicialitza totes les variables i grups per començar una nova partida."""
    global score, difficulty_level, lives, last_difficulty_update_time, spawn_interval, all_sprites, obstacles, player, projectiles
    score = 0
    difficulty_level = 1
    lives = 3
    last_difficulty_update_time = pygame.time.get_ticks()
    spawn_interval = 1500
    pygame.time.set_timer(ADD_OBSTACLE, spawn_interval)
    all_sprites = pygame.sprite.Group()
    projectiles = pygame.sprite.Group()
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
        screen.blit(background_image, (0, 0))
        draw_text(screen, "Pedra Paper i Tisora", font, BLACK, WIDTH/2, HEIGHT/2-25)
        draw_text(screen, "Prem qualsevol tecla per començar", font, RED, WIDTH/2, HEIGHT/2+25)
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
        screen.blit(background_image, (0, 0))
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

# Create a projectile and add it to sprite groups
def shoot():
    """Dispara roca (bullet)."""
    global last_shot_time

    current_time = pygame.time.get_ticks()
    if current_time - last_shot_time >= shoot_cooldown:
        projectile = Projectile(player.rect.right, player.rect.centery)
        all_sprites.add(projectile)
        projectiles.add(projectile)
        last_shot_time = current_time

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
        draw_text(screen, "Game Over!", font, RED, WIDTH/2, HEIGHT/2-50)
        draw_text(screen, "Puntuació Final: " + str(final_score), font, BLACK, WIDTH/2, HEIGHT/2-25)
        draw_text(screen, "Prem qualsevol tecla per reiniciar", font, BLACK, WIDTH/2, HEIGHT/2+25)
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
        draw_text(screen, "Pause", font, RED, WIDTH/2, HEIGHT/2-25)
        draw_text(screen, "Prem qualsevol tecla per continuar", font, BLACK, WIDTH/2, HEIGHT/2+25)
        pygame.display.flip()
# ========================
# Bucle principal del programa
# ========================

while True:
    show_menu()                   # Mostrar menú d'inici
    final_score = game_loop()       # Executar la partida
    show_game_over(final_score)     # Mostrar pantalla de Game Over i esperar reinici