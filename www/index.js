import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-app.js";
import { getFirestore, doc, setDoc, getDoc, getDocs, updateDoc, collection, onSnapshot, addDoc, deleteDoc } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-firestore.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-auth.js";
import { getFunctions, httpsCallable } from "https://www.gstatic.com/firebasejs/9.6.10/firebase-functions.js";


const firebaseConfig = {
  apiKey: "AIzaSyDTfuWsXUkOIPzc_HaprzxbgUeZ3oOzSd8",
  authDomain: "cours-itii28.firebaseapp.com",
  projectId: "cours-itii28",
  storageBucket: "cours-itii28.firebasestorage.app",
  messagingSenderId: "1029705663081",
  appId: "1:1029705663081:web:af6c551a0b23390aee98f8",
  measurementId: "G-C7M42EG9VD"
};

// Initialiser Firebase
const app = initializeApp(firebaseConfig);

// Initialiser Firestore
export const db = getFirestore(app);

// Initialiser Auth
export const auth = getAuth(app);

// Initialiser Firebase Functions
export const functions = getFunctions(app);