import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

def getMadrichName(user_id):
    db = firestore.client()
    docs = db.collection('madrichs').where('user_id', '==', str(user_id)).stream()
    madricName = ''
    for doc in docs:
       madricName = doc.to_dict()
    return madricName.get('MadrichName')
