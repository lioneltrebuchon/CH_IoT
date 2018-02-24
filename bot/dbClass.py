#!/usr/bin/env python3

class dbClass:
    # Abstractor for the database keys from the Centos MariaSQL
    lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']
    lst_zip = ['zip','PIN']
    lst_street = ['address','adress','adresse','street','Strasse','rue']

    def __init__(self):
        pass
        # self.timeOfCreation = timeOfCreation

    def wantHome(self,msg):
        if any(msg.lower() in x for x in self.lst_home):
            return true
    def wantZIP(self,msg):  
        if any(msg.lower() in x for x in self.lst_zip):
            return true
    def wantStreet(self,msg):
        if any(msg.lower() in x for x in self.lst_street):
            return true
