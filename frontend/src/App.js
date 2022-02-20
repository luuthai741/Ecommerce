import React from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import { Container } from 'react-bootstrap'
import Header from './components/Header'
import Footer from './components/Footer'
import HomeScreen from './screens/HomeScreen'
import ProductScreen from './screens/ProductScreen'
import CartScreen from './screens/CartScreen'
import LoginScreen from './screens/LoginScreen'
import RegisterScreen from './screens/RegisterScreen'
import ProfileScreen from './screens/ProfileScreen'
import ProfileUpdateScreen from './screens/ProfileUpdateScreen'
import PaymentScreen from './screens/PaymentScreen'
import PlaceOrderScreen from './screens/PlaceOrderScreen'
import OrderScreen from './screens/OrderScreen'
import UserListScreen from './screens/UserListScreen'
import ProductListScreen from './screens/ProductListScreen'
import OrderListScreen from './screens/OrderListScreen'
import ProdutFormScreen from './screens/ProductFormScxreen'
import MyOrderScreen from './screens/MyOrderScreen'
const App = () => {
  return (
    <Router>
      <Header />
      <main className='py-3'>
        <Container>
          <Route exact path='/orders' component={MyOrderScreen} />
          <Route exact path='/orders/:id' component={OrderScreen} />
          <Route path='/payment' component={PaymentScreen} />
          <Route path='/placeorder' component={PlaceOrderScreen} />
          <Route path='/login' component={LoginScreen} />
          <Route path='/register' component={RegisterScreen} />
          <Route exact path='/profile/update' component={ProfileUpdateScreen} />
          <Route exact path='/profile' component={ProfileScreen} />
          <Route path='/product/:id' component={ProductScreen} />
          <Route path='/cart' component={CartScreen} />
          <Route path='/admin/userlist' component={UserListScreen} />
          <Route
            path='/admin/products'
            component={ProductListScreen}
            exact
          />
          <Route
            exact
            path='/admin/products/:currentPage'
            component={ProductListScreen}
            exact
          />
          <Route exact path='/admin/products/:slug/:id/edit' component={ProdutFormScreen} />
          <Route exact path='/admin/product/add' component={ProdutFormScreen} />
          <Route exact path='/admin/orders' component={OrderListScreen} />
          <Route exact path='/admin/orders/currentPage' component={OrderListScreen} />
          <Route exact path='/admin/products/search/:keyword' component={ProductListScreen} />
          <Route path='/search/:keyword' component={HomeScreen} exact />
          <Route path='/page/:currentPage' component={HomeScreen} exact />
          <Route
            path='/search/:keyword/page/:pageNumber'
            component={HomeScreen}
            exact
          />
          <Route path='/' component={HomeScreen} exact />
        </Container>
      </main>
      <Footer />
    </Router>
  )
}

export default App
