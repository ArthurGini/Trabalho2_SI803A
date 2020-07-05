
import re
import nltk 
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

stop_words = set(stopwords.words('portuguese'))
stop_words.update(['.', ',', '"', "'", '?', '!', ':', ';', '(', ')', '[', ']', '{', '}'])
stop_words.update(['ter','ainda','diz','cada','porém','eu','vai','pra','deu','aqui','lá','sido','nessa','feita'])

entrada = open('entrada.txt', 'r', encoding='utf-8')

saida = open('saida.txt','w', encoding='utf-8')

list_text = entrada.readlines()

#print(list_text)
#teste = teste.join(list_text)

#string = "teste, esse. eh() um teste"

#for char in teste:
    #if not char.isalnum():
        #teste = teste.replace(char, " ")

#print(teste)

for linha in list_text:
    final = [i for i in linha.lower().split() if i not in stop_words]
    saida.writelines(i + '\n' for i in final)
