// image.js
import { localAxiosInstance } from "@/util/http-commons";

const local = localAxiosInstance;

function uploadImage(formData) {
    local.post('/upload', formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
}

function getMainImageUrl(articleno, success) {
    local.get(`/upload/${articleno}`, { responseType: 'blob' })
    .then(response => {

        if (response.status === 201) {
            success(response.data);
        } 
    });
}

export {
    uploadImage,
    getMainImageUrl
};
