import telebot

import DatabaseFunc.getMadrichName
import GeneratePic.GenerateQr
from GeneratePic import GenerateQr
from botInfo import BOT_TELEBOT_TOKEN
from DatabaseFunc import AddRecord
from DatabaseFunc import CreateRecord

bot = telebot.TeleBot(BOT_TELEBOT_TOKEN)

TimeOut = ''
TimeReturn = ''
Names = ''
GoingTo = ''


@bot.message_handler(content_types=['text'])
def start(message):
    if message.text == 'Create new Petek':
        bot.send_message(message.from_user.id, "Type the names for the Petek")
        bot.register_next_step_handler(message, get_name)
    else:
        bot.send_message(message.from_user.id, 'To create new Petek press the button.')


def get_name(message):
    global Names
    Names = message.text
    bot.send_message(message.from_user.id, 'Type Exit Time:')
    bot.register_next_step_handler(message, get_exittime)


def get_exittime(message):
    global TimeOut
    TimeOut = message.text
    bot.send_message(message.from_user.id, 'Type Return Time:')
    bot.register_next_step_handler(message, get_returntime)


def get_returntime(message):
    global TimeReturn
    TimeReturn = message.text
    bot.send_message(message.from_user.id, 'Type Place:')
    bot.register_next_step_handler(message, get_goingto)


def get_goingto(message):
    global GoingTo
    GoingTo = message.text
    Record = CreateRecord.NewJson(GoingTo, TimeReturn, DatabaseFunc.getMadrichName.getMadrichName(message.from_user.id),
                                  TimeOut, Names)
    AddRecord.AddRecord(Record)
    bot.send_message(message.from_user.id, 'Petek recorded! Confirmation code:')
    confirmation = Record.get("confirmation")
    GeneratePic.GenerateQr.generateQr(Record)
    bot.send_chat_action(message.from_user.id, 'upload_photo')
    img = open('gfg_QR.png', 'rb')
    bot.send_photo(message.from_user.id, img)
    img.close()
    print(message.from_user.id)
    bot.send_message(message.from_user.id, confirmation)


while 1:
    bot.polling()
