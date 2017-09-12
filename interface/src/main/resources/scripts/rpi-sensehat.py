import math
import sys
from pprint import pprint
from sense_hat import SenseHat
from time import sleep

sense = SenseHat()

sense.set_rotation(90)

phases = sys.argv
pprint(phases)
phases.pop(0)
pprint(phases)

if len(phases) == 0:
    print 'No colors given. Clearing HAT and exiting.'
    sense.clear()
    sys.exit()


# if len(phases) % 2 == 0:
#    print 'Equal number of colors and symbols (light phases) must be given. Clearing HAT and exiting.'
#    sense.clear()
#    sys.exit()


def getColorTuple(code):
    code = code.strip()
    # print 'Color code: ', code
    switcher = {
        "RED": (255, 0, 0),
        "GREEN": (0, 255, 0),
        "BLUE": (0, 0, 255),
        "YELLOW": (255, 255, 0),
        "PINK": (255, 0, 255),
        "ORANGE": (255, 128, 0)
    }
    return switcher.get(code, (0, 0, 0))


def getSymbolPixels(symbol, bg):
    symbol = symbol.strip()
    O = bg
    X = [255, 255, 255]

    switcher = {
        "NONE": [
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, O, O, O, O, O, O
        ],
        "ARROW_LEFT": [
            O, O, O, X, O, O, O, O,
            O, O, X, X, O, O, O, O,
            O, X, X, O, O, O, O, O,
            X, X, X, X, X, X, X, X,
            X, X, X, X, X, X, X, X,
            O, X, X, O, O, O, O, O,
            O, O, X, X, O, O, O, O,
            O, O, O, X, O, O, O, O
        ],
        "ARROW_RIGHT": [
            O, O, O, O, X, O, O, O,
            O, O, O, O, X, X, O, O,
            O, O, O, O, O, X, X, O,
            X, X, X, X, X, X, X, X,
            X, X, X, X, X, X, X, X,
            O, O, O, O, O, X, X, O,
            O, O, O, O, X, X, O, O,
            O, O, O, O, X, O, O, O
        ],
        "TOILET": [
            X, O, O, O, X, O, O, O,
            X, O, O, O, X, O, O, O,
            X, O, X, O, X, O, O, O,
            X, O, X, O, X, O, X, X,
            O, X, O, X, O, X, O, O,
            O, O, O, O, O, X, O, O,
            O, O, O, O, O, X, O, O,
            O, O, O, O, O, O, X, X
        ],
        "INFO_POINT": [
            O, O, O, X, X, O, O, O,
            O, O, O, X, X, O, O, O,
            O, O, O, O, O, O, O, O,
            O, O, X, X, X, O, O, O,
            O, O, O, X, X, O, O, O,
            O, O, O, X, X, O, O, O,
            O, O, O, X, X, O, O, O,
            O, O, X, X, X, X, O, O
        ],
        "BELL": [
            O, O, O, X, X, O, O, O,
            O, O, X, O, O, X, O, O,
            O, X, O, O, O, O, X, O,
            O, X, O, O, O, O, X, O,
            O, X, O, O, O, O, X, O,
            X, O, O, O, O, O, O, X,
            O, X, X, X, X, X, X, O,
            O, O, O, X, X, O, O, O
        ]
    }
    return switcher.get(symbol, [
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O,
        O, O, O, O, O, O, O, O
    ])


def getPixelsAnimated(j, finalPixels):
    pixels = []
    d = j / 200.0
    #print 'J: ', j, ' 3: ', int(math.ceil(d * finalPixels[0][2])), ' D: ', d, ' M: ', math.ceil(d * finalPixels[0][2]), ' Final Pixels: ', finalPixels[0:16]
    for p in finalPixels:
        pixels.append((int(math.ceil(d * p[0])), int(math.ceil(d * p[1])), int(math.ceil(d * p[2]))))
    #print 'Pixels: ', pixels[0:16]
    return pixels


i = 0
while True:
    if i == len(phases):
        i = 0
    color = phases[i]
    symbol = phases[i + 1]

    finalPixels = getSymbolPixels(symbol, getColorTuple(color))

    print 'Color: ', color, ' Symbol: ', symbol, ' final Pixels: ', finalPixels[0:16]

    j = 0
    while j < 200:
        sense.set_pixels(getPixelsAnimated(j, finalPixels))
        sleep(0.007)
        j = j + 1

    sleep(1.5)

    while j > 0:
        sense.set_pixels(getPixelsAnimated(j, finalPixels))
        sleep(0.007)
        j = j - 1

    i = i + 2
