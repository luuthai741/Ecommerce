import React from 'react'
import { Nav } from 'react-bootstrap'
import { LinkContainer } from 'react-router-bootstrap'

const CheckoutSteps = ({ step1, step2 }) => {
  return (
    <Nav className='justify-content-center mb-4'>
      <Nav.Item>
        {step1 ? (
          <LinkContainer to='/payment'>
            <Nav.Link>Phương thức thanh toán</Nav.Link>
          </LinkContainer>
        ) : (
          <Nav.Link disabled>Phương thức thanh toán</Nav.Link>
        )}
      </Nav.Item>

      <Nav.Item>
        {step2 ? (
          <LinkContainer to='/placeorder'>
            <Nav.Link>Đặt hàng</Nav.Link>
          </LinkContainer>
        ) : (
          <Nav.Link disabled>Đặt hàng</Nav.Link>
        )}
      </Nav.Item>
    </Nav>
  )
}

export default CheckoutSteps
