import sys
from pprint import pprint
from sense_hat import SenseHat
from time import sleep

sense = SenseHat()

phases = sys.argv
pprint(phases)
phases.pop(0)
pprint(phases)

if len(phases) == 0:
    print 'No colors given. Clearing HAT and exiting.'
    sense.clear()
    sys.exit()

if len(phases) % 2 == 0:
    print 'Equal number of colors and symbols (light phases) must be given. Clearing HAT and exiting.'
    sense.clear()
    sys.exit()


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
            O, O, X, X, X, X, O, O,
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
            O, O, X, X, O, O, O, O
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

    i = 0
    while True:
        if i == len(phases):
            i = 0
        color = phases[i]
        symbol = phases[i + 1]

        finalPixels = getSymbolPixels(symbol, getColorTuple(color))

        print 'Color: ', color, ' Symbol: ', symbol, ' final Pixels: ', finalPixels[0:16]

        j = 0
        while j < 255:
            pixels = []
            for p in finalPixels:
                d = j / 255
                pixels.append((d * p[0], d * p[1], d * p[2]))
            sense.set_pixels(pixels)
            sleep(5 / 1000)
            j = j + 1

        sleep(2)

        while j > 0:
            pixels = []
            for p in finalPixels:
                d = j / 255
                pixels.append((d * p[0], d * p[1], d * p[2]))
            sense.set_pixels(pixels)
            sleep(5 / 1000)
            j = j - 1

        i = i + 2
