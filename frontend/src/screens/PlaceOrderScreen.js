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
                    <h2>TH??NG TIN ?????T H??NG</h2>
                    <p>
                      <strong>Th??ng tin kh??ch h??ng:</strong>
                      {`${user.firstname} ${user.lastname} - ${user.phonenumber}`}
                    </p>
                    <p>
                      <strong>V???n chuy???n ?????n:</strong>
                      {user.address}
                    </p>
                  </ListGroup.Item>

                  <ListGroup.Item>
                    <h2>PH????NG TH???C THANH TO??N</h2>
                    <strong>Ph????ng th???c: </strong>
                    {paymentMethod}
                  </ListGroup.Item>

                  <ListGroup.Item>
                    <h2>S???N PH???M ?????T MUA</h2>
                    {cart.cartItems.length === 0 ? (
                      <Message>B???n ch??a ?????t mua s???n ph???m n??o</Message>
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
                      <h2>T???NG HO?? ????N</h2>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>S???n ph???m</Col>
                        <Col>{currencyFomatter(cart.itemsPrice)}</Col>
                      </Row>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>V???n chuy???n</Col>
                        <Col>{currencyFomatter(cart.shippingPrice)}</Col>
                      </Row>
                    </ListGroup.Item>
                    <ListGroup.Item>
                      <Row>
                        <Col>T???ng thanh to??n</Col>
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
                        ?????T H??NG
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
