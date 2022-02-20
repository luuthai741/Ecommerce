import React, { useState } from 'react'
import { Form, Button, Row, Col } from 'react-bootstrap'
import { useHistory } from 'react-router-dom'
const SearchBox = () => {
  const [keyword, setKeyword] = useState('')
  const history = useHistory();

  const submitHandler = (e) => {
    e.preventDefault();
    if (keyword !== "") {
      history.push(`/search/${keyword}`)
    }
  }

  return (
    <Form onSubmit={submitHandler}>
      <Row>
        <Col xs="auto">
          <Form.Control
            type='text'
            name='q'
            onChange={(e) => setKeyword(e.target.value)}
            placeholder='Sản phẩm bạn cần tìm là...'
            className=""
          ></Form.Control>
        </Col>
        <Col xs="auto">
          <Button disabled={!keyword} type='submit' variant='outline-success' className='p-2'>
            Tìm kiếm
          </Button>
        </Col>
      </Row>
    </Form>

  )
}

export default SearchBox
