import admin from 'firebase-admin'
import { getFirestore } from 'firebase-admin/firestore'

if (!admin.apps.length) {
    const serviceAccount = JSON.parse(process.env.FIREBASE_SERVICE_ACCOUNT_KEY as string)
    admin.initializeApp({
        credential: admin.credential.cert(serviceAccount as admin.ServiceAccount),
        databaseURL: process.env.FIREBASE_DATABASE_URL
    })
}

export const db = getFirestore()

// Firestore 유틸리티 함수들
export async function get<T>(collection: string, id: string): Promise<T | null> {
    const doc = await db.collection(collection).doc(id).get()
    return doc.exists ? (doc.data() as T) : null
}

export async function put(collection: string, id: string, data: any): Promise<void> {
    await db.collection(collection).doc(id).set(data)
}

export async function update(collection: string, id: string, data: any): Promise<void> {
    await db.collection(collection).doc(id).update(data)
}

export async function remove(collection: string, id: string): Promise<void> {
    await db.collection(collection).doc(id).delete()
}

export async function query<T>(
    collection: string,
    conditions?: { field: string; operator: FirebaseFirestore.WhereFilterOp; value: any }[]
): Promise<T[]> {
    let query: FirebaseFirestore.Query = db.collection(collection)

    if (conditions) {
        conditions.forEach(({ field, operator, value }) => {
            query = query.where(field, operator, value)
        })
    }

    const snapshot = await query.get()
    return snapshot.docs.map(doc => ({ id: doc.id, ...doc.data() }) as T)
} 