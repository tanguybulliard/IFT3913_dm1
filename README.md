# IFT3913_dm1

Tanguy Bulliard 20126244
Colin Parent 20143707

Vendredi 11 février 2022

Lien github:
https://github.com/tanguybulliard/IFT3913_dm1.git

Le JAR se situe dans /out/artifacts

COMMENT COMPILE:

Il suffit de:
- mettre le path du package à analyser dans la classe Main à l'attribut "String path"
- mettre le path du document csv qui va recevoir les données classes dans String pathExcelClass
- mettre le path du document csv qui va recevoir les données paquets dans String pathExcelPaquet

Exemple:

Excel.classFile(path,pathExcelClass)
ou 
Excel.paquetFile(path,pathExcelPaquet);
