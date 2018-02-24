#!/usr/bin/env python3

class dbClassObj:
    # Abstractor for the database keys from the Centos MariaSQL
    lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']
    lst_zip = ['zip','PIN']
    nbrs_zip = [5]
    lst_street = ['address','adress','adresse','street','Strasse','rue']
    lst_address = ['where','location','address','adresse']

    def __init__(self):
        pass
        # self.timeOfCreation = timeOfCreation

    def wantHome(self,msg):
        if any(msg.lower() in x for x in self.lst_home):
            return True
    def wantZIP(self,msg):  
        if any(msg.lower() in x for x in self.lst_zip) or (self.is_number(msg) and len(msg)==5):
            return True
    def wantStreet(self,msg):
        if any(msg.lower() in x for x in self.lst_street):
            return True
    def wantAddress(self,msg):
        if any(msg.lower() in x for x in self.lst_address):
            return True

    def is_number(self,s):
        try:
            float(s)
            return True
        except ValueError:
            pass
     
        try:
            import unicodedata
            unicodedata.numeric(s)
            return True
        except (TypeError, ValueError):
            pass
     
        return False
            