import axiosInstance from "../config/axiosConfig";

export const getSubjects = async () => {
  try {
    const response = await axiosInstance.get(`/subject`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching subjects.`, error);
    throw error;
  }
};
