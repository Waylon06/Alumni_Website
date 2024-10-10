import axios from "./axios.js";
import qs from 'qs'

export const clickToSignInAPI = (params) => axios.post('/api/user/login', qs.parse(params));
export const clickToSignUpAPI = (params) => axios.post('/api/user/register', qs.parse(params));
export const changeUserInfoAPI = (params) => axios.post('api/user/change', qs.parse(params))
export const getNoticeInfoAPI = () => axios.get('/api/notice/all');
export const getMajorInfoAPI = () => axios.get('/api/major/all');
export const getActivityInfoAPI = (params) => axios.get('/api/activity/all', {params});
export const getClassInfoAPI = () => axios.get('/api/classes/all'); 
export const getStudentInfoAPI = (params) => axios.get('/api/student/all', {params});
export const getPicUrlAPI = () => axios.post('/api/uploadImg/upload');
export const getActivityByIdAPI = (params) => axios.get('/api/activity/getActivityById', {params});
export const addActivityAPI = (params) => axios.get('/api/activity/add', {params});
export const addStudentAPI = (params) => axios.get('/api/student/add', {params});
export const getStudentByUidAPI = (params) => axios.get('/api/student/findById', {params});
export const getUserInfoAPI = (params) => axios.get('/api/user/all', {params});
export const addFeedbackAPI = (params) => axios.get('/api/feedback/add', {params});
export const delActivityAPI = (params) => axios.delete('/api/activity/del', {params});
export const editActivityAPI = (params) => axios.get('/api/activity/change', {params});
