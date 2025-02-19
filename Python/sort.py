import random

def ordena(lista):
    i = 1
    plus = []
    minus = []
    final = []

    while (i < len(lista)):
        if (lista[0] > lista[i]):
            minus.append(lista[i])
        elif (lista[0] <= lista[i]):
            plus.append(lista[i])
        i += 1
    if (len(minus) > 0):
        minus = ordena(minus)
    if (len(plus) > 0):
        plus = ordena(plus)
    for i in minus:
        final.append(minus[i])
    final.append(lista[0])
    for i in plus:
        final.append(plus[i])
    return lista

numeros = [random.randint(1, 100000) for _ in range(10000)]
numeros_ordenados = ordena(numeros)
print (numeros_ordenados)