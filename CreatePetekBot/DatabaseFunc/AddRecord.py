import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore
import uuid

cred = credentials.Certificate('private_key.json')
firebase_admin.initialize_app(cred)
db = firestore.client()
newId = str(uuid.uuid4().hex)
def AddRecord (Record):
    db.collection(u'confirms').document(newId).set(Record)
