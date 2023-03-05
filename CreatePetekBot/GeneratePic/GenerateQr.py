import qrcode
from PIL import Image

def generateQr(Record):
    confirmation = Record.get('confirmation')
    Logo_link = 'logo.jpeg'
    logo = Image.open(Logo_link)
    basewidth = 100
    wpercent = (basewidth / float(logo.size[0]))
    hsize = int((float(logo.size[1]) * float(wpercent)))
    logo = logo.resize((basewidth, hsize), Image.ANTIALIAS)
    QRcode = qrcode.QRCode(
        error_correction=qrcode.constants.ERROR_CORRECT_H
    )
    confirm = confirmation
    QRcode.add_data(confirm)
    QRcode.make()
    QRcolor = 'Green'
    QRimg = QRcode.make_image(
        fill_color=QRcolor, back_color="white").convert('RGB')
    pos = ((QRimg.size[0] - logo.size[0]) // 2,
           (QRimg.size[1] - logo.size[1]) // 2)
    QRimg.paste(logo, pos)
    QRimg.save('gfg_QR.png')

