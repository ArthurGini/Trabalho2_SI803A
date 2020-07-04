import nltk 
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize

stop_words = set(stopwords.words('portuguese'))
stop_words.update(['.', ',', '"', "'", '?', '!', ':', ';', '(', ')', '[', ']', '{', '}'])

#text = "Eu de américo brasiliense, gosto de : bolach ; a porque quando eu choro me entrsitece"

with open("entrada.txt", "r") as f:
    text = list(f)


for linha in text:
    print ([i for i in linha.lower().split() if i not in stop_words])

