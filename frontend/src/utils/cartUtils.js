const cartUtils = {
    setPaymentMethod: (data) => {
        return localStorage.setItem('paymentMethod', JSON.stringify(data));
    },
    getPaymentMethod: () => {
        return JSON.parse(localStorage.getItem('paymentMethod'));
    }
}
export default cartUtils;