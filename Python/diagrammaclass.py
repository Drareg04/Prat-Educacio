class Projecte:
    def __init__(self, nom, duracio,llenguatge):

        self.nom = nom
        self.duracio = duracio
        self.llenguatge = llenguatge
        self.tasques = []
        self.equips = []

    def show_info(self):
        return  (f"PROJECTE: {self.nom}, DURADA: {self.duracio} mesos de temps, LLENGUATGE: {self.llenguatge}")

    def add_task(self, tasca):
        self.tasques.append(tasca)

    def show_tasks(self):
        tasquesinformacio = " "
        for tasca in self.tasques:
            tasques_informacio += f"TASCA: {tasca.titol} ESTAT: {tasca.estat} RESPONSABLE: {tasca.membre.nom}\n"
        return tasques_informacio

class ProjecteIntern(Projecte):
    def __init__(self, nom, duracio,llenguatge,responsable,departament):
        super().__init__(nom, duracio,llenguatge)
        self.responsable = responsable
        self.departament = departament

    def show_info(self):
        info = super().show_info()
        info += f", Responsable: {self.responsable}, Departament: {self.departament}"
        return info

class ProjecteExtern(Projecte):
    def __init__(self, nom, duracio, llenguatge, client,pressupost):
        super().__init__(nom, duracio, llenguatge)
        self.client = client
        self. pressupost = pressupost

    def show_info(self):
        info = super().show_info()
        info += f", Client: {self.client}, Pressupost: {self.pressupost}K€"
        return info

class Equip:
    def __init__(self,nomEquip):
        self.nomEquip = nomEquip
        self.membres = []

    def add_member(self, membre):
        self.membres.append(membre)

    def show_member(self):
        info_membres = (f" ")
        for membre in self.membres:
            info_membres += f"Nom del MEMBRE: {membre.nom} ROL: {membre.rol}, {membre.experiencia} anys d'experiència\n"
        return info_membres

    def show_info(self):
        return (f"{self.nomEquip}, Membres: 2")

class Membre:
    def __init__(self, nom,rol,experiencia):
        self.nom = nom
        self.rol = rol
        self.experiencia = experiencia

class Tasca:
    def __init__(self,titol,estat,membre):

        self.titol = titol
        self.estat = estat
        self.membre = membre

if __name__ == "__main__":
    projecte_intern = ProjecteIntern(
        nom="Aplicació CRM Interna",
        duracio=12,
        llenguatge="Python",
        responsable="Joan Rovira",
        departament="IT"
    )

    projecte_extern = Projecte(
        nom="Plataforma E-learning",
        duracio=18,
        llenguatge="Java",
        client="Educorp",
        pressupost=300
    )

    equip = Equip("Equip Desenvolupament")
    membre1 = Membre("Anna", "Desenvolupadora", 3)
    membre2 = Membre("Marc", "Tester", 2)
    equip.add_member(membre1)
    equip.add_member(membre2)

    tasca1 = Tasca("Definir requeriments", "pendent", membre1)
    tasca2 = Tasca("Provar funcionalitats", "pendent", membre2)
    projecte_intern.add_task(tasca1)
    projecte_intern.add_task(tasca2)

    print("Informació del projecte intern:")
    print(projecte_intern.show.info())
    print("\nTasques del projecte intern:")
    print(projecte_intern.show_tasks())

    print("\nInformació de l'equip:")
    print(equip.show_info())
    print("\nMembres de l'equip:")
    print(equip.show_members())

    print("\nInformació del projecte extern:")
    print(projecte_extern.show_info())