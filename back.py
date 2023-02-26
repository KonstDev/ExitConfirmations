import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore
import uuid

# Инициализируем приложение Firebase с помощью учетных данных
cred = credentials.Certificate('private_key.json')
firebase_admin.initialize_app(cred)

# Получаем доступ к базе данных Firestore
db = firestore.client()

# Выполняем запрос, чтобы найти все документы, у которых значение поля "city" равно "Moscow"
docs = db.collection('confirms').where('confirmation', '==', '285674289562973592527').stream()

# Выводим результаты
#for doc in docs:
   # print(f'{doc.id} => {doc.to_dict()}')


newId = str(uuid.uuid4().hex)
print (newId)
confirmation = str(uuid.uuid4().hex)
newConf = {
    u'GoingTo': u'Los Angeles',
    u'confirmation': confirmation,
    u'ReturnTime': u'17:00:00',
    u'MadrichName': u'Barak',
    u'ExitTime': u'15:00:00',
	u'StudentName': u'Barak Obama'
}
db.collection(u'confirms').document(newId).set(newConf)