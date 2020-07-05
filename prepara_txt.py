
import re
import nltk 
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

stop_words = set(stopwords.words('portuguese'))
stop_words.update(['.', ',', '"', "'", '?', '!', ':', ';', '(', ')', '[', ']', '{', '}','-','_'])
stop_words.update(['faz','to','for','tipo','a','mó','seis','há','que','cá','cês','tem','lá','to','dá','ve','ia','vez','uns','quê','ai','si','né','aí','não','é','tô','tá','ter','ainda','diz','cada','porém','eu','vai','pra','deu','aqui','lá','sido','nessa','feita'])
stop_words.update(['o','ô','ó','eu','vou','ró','vao','vão','pá','rá','pô'])
stop_words.update(['1','2','3','4','5','6','7','8','9',''])

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
