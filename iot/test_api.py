#! /bin/python3

import requests
import time

ADDR = "52.14.216.215"
PORT = "8080"
PATH = "/rest/request"

def client_send(vibration, noise, humidity, temperature, light):
	info = {
		"usecase": 0,
		"city": "st. gallen"
		}
	print(info)
	res = requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	# ~ addr = "http://"+ADDR+":"+PORT+PATH
	# ~ res = requests.get(addr)
	print(res.text)
	return 0

while(1):
	client_send(12, 15, 20, 15, 16)
	time.sleep(2)
