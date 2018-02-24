#!/usr/bin/env python3
import urllib
from requests import get
from datetime import datetime, timedelta
from dateutil import tz
from textblob import TextBlob
import time
import logging
import dbClass as dbClass

# constants
LOOPTIMEOUT = 5 # in seconds
URL_BOT = "https://api.telegram.org/bot433042847:AAGUrcfu9FPgwd942dvFUiidG45FuFzoRpg/"
URL_API = "TODO"
IP_ADDR = "18.222.52.158"
LISTEN_REQUEST = "/rest/request"
URL_HOME = URL_API + "/events"
URL_HOME_LIST = URL_HOME + "/events?sort=-_time_start"


#
# Solve the use cases
#
def uc0_send(city):
	info = {
		"usecase": 0,
		"city": city
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	print(res)
	return res.text
	
def uc1_send(houseid):
	info = {
		"usecase": 1,
		"house": houseid
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	print(res)
	return 0
	
def uc2_send(city):
	info = {
		"usecase": 0,
		"city": city
		}
	print("To be sent: "+str(info))
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	print(res)
	return 0





#
# Functions to Telegram API
#
def get_updates(offset=None):
    url = URL_BOT + "getUpdates?timeout=10"
    if offset:
        url += "&offset={}".format(offset)
    return get(url).json()

def get_last_update_id(updates):
    update_ids=[]
    for update in updates["result"]:
        update_ids.append(int(update["update_id"]))
    return max(update_ids)

def receive_input_string():
    send_message("TODO This function is not yet implemented", chat)


def send_message(text, chat_id):
    # text = urllib.parse.quote_plus(text)
    url = URL_BOT + "sendMessage?parse_mode=markdown&text={}&chat_id={}".format(text, chat_id)
    get(url)

def get_home(indexDevice):
    resp = get(URL_HOME + indexDevice).json()
    if "_status" in resp:
        return None
    else:
        return resp

def help(chat_id):
    HELP_TEXT = """Help!"""
    send_message(HELP_TEXT, chat_id)

def initialize_prop_lists():
    lst_home = ['home','homes','appart','appartment','apt','house','room','Zimmer','Wohnung']


def main():
    logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',level=logging.INFO)
    last_update_id = None
    firstTime = True
    id_array = []
    db = dbClass.dbClassObj() # TODO add time of creation
    while True:
        # read all new messages from Telegram bot
        updates=get_updates(last_update_id)
        if ("result" in updates) and (len(updates["result"]) > 0):
            last_update_id = get_last_update_id(updates)+1
        else:
            continue
        print(updates)
        # discard the first batch of valid updates to not spam users from old messages
        if firstTime:
            firstTime = False
            continue
        # for every new /event chat message, call send events
        for r in updates["result"]:
            print(r)
            if "text" in r["message"]:
                msg = r["message"]["text"].lower()
                chat = r["message"]["chat"]["id"]
                id_array.append(chat)
                city=db.wantHomeAndCity(msg)
                if(city!=False):
                    resp=uc0_send(city)
                elif db.wantHome(msg):
                    send_message("In which city would you like to live ?", chat)
                elif db.wantSecure(msg):
                    send_message("The criminality is 5.2%, it's a quiet safe neighborhood.", chat)
                elif db.wantComfortable(msg):
                     pass
					
                # ~ elif db.wantZIP(msg):
                    # ~ send_message("recognized zip", chat)
                # ~ elif db.wantStreet(msg):
                    # ~ send_message("recognized street", chat)
        # pause the loop
        time.sleep(LOOPTIMEOUT)

if __name__ == '__main__':
    main()
