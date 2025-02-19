while True:
    age = input("Pleas, insert your age ")
    if age != "0":
        if age < "18":
            print("You are to young to enter here")
        elif age >= "18":
            print("Welcome, what do you want?")
        else:
            print("Write a valid age")
    elif age == "0":
        print("Stop joking around")
