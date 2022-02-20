import React, { useEffect } from 'react'
import { Link, useHistory } from 'react-router-dom'
import { Button, Row, Col, ListGroup, Image, Card } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Message from '../components/Message'
import CheckoutSteps from '../components/CheckoutSteps'
import { createOrder } from '../actions/orderActions'
import { getUserDetails } from '../actions/userActions'
import { currencyFomatter } from '../utils/currencyUtils';
import Loader from '../components/Loader'

const PlaceOrderScreen = () => {
  const dispatch = useDispatch()
  const history = useHistory();
  const cart = useSelector((state) => state.cart);
  const { paymentMethod } = cart;

  if (paymentMethod === "") {
    history.push('/payment')
  }
  if (cart.cartItems.length === 0) {
    history.push("/cart")
  }

  const { userInfo } = useSelector(state => state.userLogin);
  const { user } = useSelector(state => state.userDetails);
  useEffect(() => {
    if (!userInfo) {
      return;
    }
    dispatch(getUserDetails(userInfo.id))
  }, []);

  const addDecimals = (num) => {
    return (Math.round(num * 100) / 100)
  }

  cart.itemsPrice = addDecimals(
    cart.cartItems.reduce((acc, item) => acc + item.price * item.qty, 0)
  )

  cart.shippingPrice = addDecimals(cart.itemsPrice > 50000000 || cart.cartItems.length === 0 ? 0 : 30000)
  cart.totalPrice = (
    Number(cart.itemsPrice) +
    Number(cart.shippingPrice)
  )

  const orderCreate = useSelector((state) => state.orderCreate)
  const { order, success, error, loading } = orderCreate

  const placeOrderHandler = () => {
    let cartItems = [];
    cart.cartItems.forEach((x) => {
      let item = {
        productId: x.id,
        productName: x.name,
        quatity: x.qty,
        itemPrice: x.price,
        total: x.qty * x.price
      };
      cartItems.push(item);
    })
    console.log(cartItems);
    dispatch(
      createOrder({
        userId: userInfo.id,
        username: `${user.firstname} ${user.lastname}`,
        address: user.address,
        phonenumber: user.phonenumber,
        shippingCost: cart.totalPrice > 50000000 ? 0 : 30000,
        paymentMethod: paymentMethod,
        total: cart.totalPrice,
        cartItems: cartItems,
      })
    )
  }
  return (
    <div>
      {loading ? (<Loader />) :
        (
          <>
            <CheckoutSteps />
            <Row>
              <Col md={8}>
                <ListGroup variant='flush'>
                  <ListGroup.Item>
                    <h2>THÔNG TIN ĐẶT HÀNG</h2>
                    <p>
                      <strong>Thông tin khách hàng:</strong>
                      {`${user.firstname} ${user.lastname} - ${user.phonenumber}`}
                    </p>
                    <p>
                      <strong>Vận chuyển đến:</strong>
                      {user.address}
                    </p>
                  </ListGroup.Item>

                  <ListGroup.Item>
                    <h2>PHƯƠNG THỨC THANH TOÁN</h2>
                    <strong>Phương thức: </strong>
                    {paymentMethod}
                  </ListGroup.Item>

                  <ListGroup.Item>
                    <h2>SẢN PHẨM ĐẶT MUA</h2>
                    {cart.cartItems.length === 0 ? (
                      <Message>Bạn chưa đặt mua sản phẩm nào</Message>
                    ) : (
                      <ListGroup variant='flush'>
                        {cart.cartItems.map((item, index) => (
                          <ListGroup.Item key={index}>
                            <Row>
                              <Col md={1}>
                                <Image
                                  src={item.imageURL}
                                  alt={item.imageName}
                                  fluid
                                  rounded
                                />
                              </Col>
                              <Col>
                                <Link to={`/product/${item.id}`}>
                                  {item.name}
                                </Link>
                              </Col>
                              <Col md={4}>
                                {item.qty} x {currencyFomatter(item.price)} = ${currencyFomatter(item.qty * item.price)}
                              </Col>
                            </Row>
                          </ListGroup.Item>
                        ))}
                      </ListGroup>
                    )}
                  </ListGroup.Item>
                </ListGroup>
              </Col>
              <Col md={4}>
                <Card>
                  <ListGroup variant='flush'>
                    <ListGroup.Item>
                      <h2>TỔNG HOÁ ĐƠN</h2>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>Sản phẩm</Col>
                        <Col>{currencyFomatter(cart.itemsPrice)}</Col>
                      </Row>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>Vận chuyển</Col>
                        <Col>{currencyFomatter(cart.shippingPrice)}</Col>
                      </Row>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>Tổng thanh toán</Col>
                        <Col>{currencyFomatter(cart.totalPrice)}</Col>
                      </Row>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      {error && <Message variant='danger'>{error}</Message>}
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Button
                        type='button'
                        className='btn-block'
                        disabled={cart.cartItems === 0}
                        onClick={placeOrderHandler}
                      >
                        ĐẶT HÀNG
                      </Button>
                    </ListGroup.Item>
                  </ListGroup>
                </Card>
              </Col>
            </Row>
          </>
        )

      }
    </div>
  )
}

export default PlaceOrderScreen
