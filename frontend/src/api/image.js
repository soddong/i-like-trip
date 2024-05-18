// image.js
import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

function uploadImage(formData, success, fail) {
    local.post('/upload', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
    .then(success)
    .catch(fail);
}

function getMainImageUrl(articleno, success, fail) {
    local.get(`/upload/${articleno}`, { responseType: 'blob' })
    .then(response => {

        if (response.status === 201) {
            success(response.data);
        } 
    })
    .catch(error => {
        console.error('Failed to fetch image URL:', error);
        fail(error);
    });
}

export {
    uploadImage,
    getMainImageUrl
};
