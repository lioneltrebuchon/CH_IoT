#! /bin/python3

import requests
import time

ADDR = "18.219.99.213"
PORT = "8080"
PATH = "/insert"

def client_send(vibration, noise, humidity, temperature, light):
	info = {
		"id": "1",
		"timestamp": time.time(),
		"vibration": vibration,
		"noise": noise,
		"humidity": humidity,
		"temperature": temperature,
		"light": light
		}
	print(info)
	requests.post("http://"+ADDR+":"+PORT+PATH, json=info)
	return 0

while(1):
	client_send(12, 15, 20, 15, 16)
	time.sleep(2)
