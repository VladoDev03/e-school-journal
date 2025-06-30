import axiosInstance from "../config/axiosConfig";

export const createSchool = async (schoolData) => {
  try {
    const response = await axiosInstance.post("/school", schoolData);
    return response.data;
  } catch (error) {
    console.error("School creation error:", error);
    throw error;
  }
};

export const getAllSchools = async () => {
  try {
    const response = await axiosInstance.get("/school");
    return response.data;
  } catch (error) {
    console.error("Error fetching all schools:", error);
    throw error;
  }
};
