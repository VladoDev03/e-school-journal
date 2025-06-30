import axiosInstance from "../config/axiosConfig";

export const addQualification = async (data) => {
  try {
    const response = await axiosInstance.post(`/teacher/qualification`, data);
    return response.data;
  } catch (error) {
    console.error(`Error adding qualification to teacher.`, error);
    throw error;
  }
};