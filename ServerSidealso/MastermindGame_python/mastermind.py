#! /usr/bin/python

import cgi
import random

#using FieldStorage to get values of the html elements 
form = cgi.FieldStorage()

reds = 0
whites = 0
wrongs = 0

#Checking if a random number to be guessed is generated or not
#If not generated then generate one , else obtain it from the html element
if "answer" in form:
	answer = form.getvalue("answer")
else:
	answer=""
	for i in range(4)
		answer += random.randint(0,9)


#Getting the input entered by user
if "guess" in form:
	guess = form.getvalue("guess")
	#Need to check the input with the random number and printout whites or reds accordingly
	for key,value in enumerate(guess): #Using enumerate function to iterate thorugh input characters and maintaining a count
		if value == answer[key]:
			reds += 1
		else:
			wrongs += 1
			for ansDigit in answer:
				if ansDigit == value:
					whites += 1
					break
else:
	guess = ""



#Having a counter for the no of attempts done while guessing
if "noOfGuesses" in form:
	totalGuesses = form.getvalue("noOfGuesses")
else:
	totalGuesses = 0



if totalGuesses == 0:
	message = "I have chosen a 4 digit number. Can you guess it?"
elif reds = 4:
	message = "Well Done!! You have completed it in "+ str(totalGuesses) + "guesses"
else:
	message = "You have " + str(reds) + " correct digit(s) in the right place, " + str(wrongs) + " wrong digit(s) and " + str(whites) + " correct digit(s) but in the wrong place. You have completed " + str(numberOfGuesses) + " guess(es)."




print('Content-type: text/html')
print('')

print('<h1>Mastermind Game</h1>')
print('<h4>'+ message +'</h4>')
print('<form method="post">')
print('<input type="text" name="guess"  value= "' + guess +'">')
print('<input type="hidden" name="answer" value= "'+ answer + '">')
print('<input type="hidden" name="noOfGuesses" value= "'+ totalGuesses + '">')
print('<input type="submit" value="Guess!!"')
print('</form>')




