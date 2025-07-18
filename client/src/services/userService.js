import axiosInstance from "../config/axiosConfig";

export const getUserByEmail = async (email) => {
  try {
    const response = await axiosInstance.get(`/user/${email}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching user with email ${email}:`, error);
    throw error;
  }
};

export const getNotAssignedRoles = async (id) => {
  try {
    const response = await axiosInstance.get(`/user/possible-roles/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching roles.`, error);
    throw error;
  }
};

export const addTeacherRole = async (id) => {
  try {
    const response = await axiosInstance.post(`/teacher/add-role/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error adding role to user with ID ${id}:`, error);
    throw error;
  }
};

export const updateUser = async (id, newData) => {
  try {
    const response = await axiosInstance.put(`/user/${id}`, newData);
    return response.data;
  } catch (error) {
    console.error(`Error updating user with ID ${id}:`, error);
    throw error;
  }
};
