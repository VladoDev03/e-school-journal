import axiosInstance from "../config/axiosConfig";

export const createParent = async (parentData) => {
  try {
    const response = await axiosInstance.post("/parent", parentData);
    return response.data;
  } catch (error) {
    console.error("Parent registration error:", error);
    throw error;
  }
};

export const getParentById = async (id) => {
  try {
    const response = await axiosInstance.get(`/parent/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching parent with ID ${id}:`, error);
    throw error;
  }
};

export const getAllParents = async () => {
  try {
    const response = await axiosInstance.get("/parent");
    return response.data;
  } catch (error) {
    console.error("Error fetching all parents:", error);
    throw error;
  }
};

// Fixed: Updated to match the component's usage
export const updateParent = async (id, parentData) => {
  try {
    const response = await axiosInstance.put(`/parent/${id}`, parentData);
    return response.data;
  } catch (error) {
    console.error(`Error updating parent with ID ${id}:`, error);
    throw error;
  }
};

export const deleteParentById = async (id) => {
  try {
    const response = await axiosInstance.delete(`/parent/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error deleting parent with ID ${id}:`, error);
    throw error;
  }
};
