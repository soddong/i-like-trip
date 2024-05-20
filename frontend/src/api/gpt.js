import { localAxiosInstance } from "@/util/http-commons";

export function getTopPlaces() {
    return localAxiosInstance.get('/gpt');
}