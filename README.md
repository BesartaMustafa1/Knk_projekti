# KNK Projekti

## Universiteti Hasan Prishtina

## Fakulteti i Inxhinierisë Elektrike dhe Kompjuterike / Departamenti i Inxhinierisë Kompjuterike dhe Softuerike

## Detyra: Menaxhimi i Bibliotekës

#### Profesorët:
Ligjerata: Isak Shabani  
Ushtrimet: [Blend Arifaj](https://github.com/BlendArifaj)

### Përshkrimi i Projektit

Ky projekt është një aplikacion në JavaFX që ka për qëllim të tregojë informacion mbi librat dhe studentët që i kanë huazuar librat.

Aplikacioni ofron:

- Paraqitjen e tabelës së librave dhe stokut të tyre
- Numrin e studentëve që kanë marrë libra
- Faqen e librave (këtu mundësohet insertimi, fshirja dhe përditësimi i librave)
- Faqen e studentëve (këtu mundësohet insertimi, fshirja dhe përditësimi i studentëve)
- Faqen e rikthimit të librit (këtu mundësohet rikthimi i librit, filtrimi i ID-së së studentit dhe librit)

### Strukturë e Projektit

Projekti është i organizuar në këto paketa:

1. **app** ->Përmban klasat e aplikacionit dhe menaxhimin e sesioneve. 
2. **Controllers** ->Përmban klasat e kontrolluesve për menaxhimin e logjikës së aplikacionit dhe ndërfaqes grafike.
3. **DataBase** ->Përmban klasat dhe konfigurimet që lidhen me bazën e të dhënave. 
4. **Model** -> Përmban klasat e modeleve për përfaqësimin e të dhënave.
5. **Repository** ->Përmban klasat që janë përgjegjëse për ndërveprimin me bazën e të dhënave përmes metodave të CRUD-it (Krijo, Lexo, Përditëso, Fshi). 
6. **Service** ->Këtu gjenden klasat për ofrimin e shërbimeve dhe ndërveprimin me bazën e të dhënave.
7. **resources** ->Këtu gjenden skedarët FXML dhe burimet e tjera të nevojshme për aplikacionin. 

