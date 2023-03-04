import uuid

def NewJson(GoingTo, ReturnTime, MadrichName, ExitTime, StudentName):
    confirmation = str(uuid.uuid4().hex)
    newConf = {
        u'GoingTo': GoingTo,
        u'confirmation': confirmation,
        u'ReturnTime': ReturnTime,
        u'MadrichName': MadrichName,
        u'ExitTime': ExitTime,
        u'StudentName': StudentName}
    print(StudentName)
    return newConf
