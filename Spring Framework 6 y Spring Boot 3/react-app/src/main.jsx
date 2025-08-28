import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { ProductApp } from './components/ProductApp'
// import './index.css'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ProductApp title={ 'Lista de productos!' }/>
  </StrictMode>,
)
