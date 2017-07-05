import sys

from sense_hat import SenseHat
from time import sleep
from pprint import pprint

sense = SenseHat()

colors = sys.argv
pprint(colors)
colors.pop(0)
pprint(colors)

if len(colors) == 0:
    print 'No colors given. Clearing HAT and exiting.'
    sense.clear()
    sys.exit()


def getColorTuple(code):
    code = code.strip()
    #print 'Color code: ', code
    switcher = {
        "RED": (255, 0, 0),
        "GREEN": (0, 255, 0),
        "BLUE": (0, 0, 255),
        "YELLOW": (255, 255, 0),
        "PINK": (255, 0, 255),
        "ORANGE": (255, 128, 0)
    }
    return switcher.get(code, (0, 0, 0))


i = 0
while True:
    if i == len(colors):
        i = 0
    c = colors[i]
    #print 'Showing color ', c, '.'
    sense.show_letter('V', (255, 255, 255), getColorTuple(c))
    sleep(2)
    i = i + 1
