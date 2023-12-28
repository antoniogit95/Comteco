const canbiar = false //true servidor, false local

const LOCAL = "http://localhost:8080"
const SERVER = "http://192.168.1.76:8080/backend"
const endPoint = canbiar? SERVER : LOCAL; 

export const URL_API_public = `${endPoint}/auth`
export const URL_API_private = `${endPoint}/api/v1`;
