import React, { useState } from 'react'
import { Form, Button, Row, Col } from 'react-bootstrap'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router-dom'
import debounce from 'lodash/debounce'

const AdminSearchBox = () => {
  const [keyword, setKeyword] = useState("");
  const history = useHistory();

  const handleChange = value => {
    if (value) {
      history.push(`/admin/products/search/${value}`)
    }
    if (value === "") {
      history.push(`/admin/products`)
    }
  }

  const debounceOnChange = debounce((value) => {
    handleChange(value);
  }, 1000);



  return (
    <Form>
      <Row>
        <Col xs="auto">
          <Form.Control
            type='text'
            name='q'
            onChange={e => debounceOnChange(e.target.value)}
            placeholder='Sản phẩm cần tìm là ...'

          ></Form.Control>
        </Col>
      </Row>
    </Form >

  )
}

export default AdminSearchBox
