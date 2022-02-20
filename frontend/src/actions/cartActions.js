import axios from 'axios'
import * as types from '../constants/cartConstants'
import cartUtils from '../utils/cartUtils';

export const addToCart = (id, qty) => async (dispatch, getState) => {
  const { data } = await axios.get(`/api/v1/products/${id}`)
  dispatch({
    type: types.CART_ADD_ITEM,
    payload: {
      id: id,
      name: data.name,
      imageName: data.imageName,
      imageURL: data.imageURL,
      price: data.price,
      quantity: data.quantity,
      qty: parseInt(qty),
    },
  })
  localStorage.setItem('cartItems', JSON.stringify(getState().cart.cartItems))
}
export const updateCartItem = (id, qty) => async (dispatch, getState) => {
  dispatch({
    type: types.CART_UPDATE_ITEM,
    payload: {
      id: id,
      qty: parseInt(qty),
    },
  })
  localStorage.setItem('cartItems', JSON.stringify(getState().cart.cartItems))
}

export const removeFromCart = (id) => (dispatch, getState) => {
  dispatch({
    type: types.CART_REMOVE_ITEM,
    payload: id,
  })
  const cartItems = getState().cart.cartItems;
  if (cartItems.length > 0) {
    localStorage.setItem('cartItems', JSON.stringify(getState().cart.cartItems));
  } else {
    localStorage.removeItem('cartItems');
  }
}

export const savePaymentMethod = (data) => (dispatch, getState) => {
  dispatch({
    type: types.CART_SAVE_PAYMENT_METHOD,
    payload: data,
  })
  cartUtils.setPaymentMethod(data, getState().cart.paymentMethod);
}
