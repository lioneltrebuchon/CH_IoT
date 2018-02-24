#!/usr/bin/env python3

class dbClass:
    # Abstractor for the database keys from the Centos MariaSQL
    lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']
    lst_zip = ['ZIP','zip','Zip','PIN']
    lst_street = ['address','adress','adresse','Adresse','street','Strasse','rue']

    def __init__(self, timeOfCreation):
        self.timeOfCreation = timeOfCreation

    def wantHome(self,msg):
        if any(msg in x for x in self.lst_home):
            return true
    def wantZIP(self,msg):  
        if any(msg in x for x in self.lst_zip):
            return true
    def wantStreet(self,msg):
        if any(msg in x for x in self.lst_street):
            return true
