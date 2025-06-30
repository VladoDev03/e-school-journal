import axiosInstance from "../config/axiosConfig";

export const createDirector = async (directorData) => {
  try {
    const response = await axiosInstance.post("/director/school-id", directorData);
    return response.data;
  } catch (error) {
    console.error("Director creation error:", error);
    throw error;
  }
};

export const getAllDirectors = async () => {
  try {
    const response = await axiosInstance.get("/director/with-schools");
    return response.data;
  } catch (error) {
    console.error("Error fetching all directors:", error);
    throw error;
  }
};
