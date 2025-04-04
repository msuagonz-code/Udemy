import { useEffect, useState } from 'react';
import { ProductTable } from './components/ProductTable';
import { ProductForm } from './components/ProductForm';
import PropTypes from "prop-types"

const initProducts = [{
    id: 1,
    name: 'Monitor Asus 37',
    description: 'El monitor es perfecto para juegos!',
    price: 1000
},
{
    id: 2,
    name: 'Iphone 16 pro',
    description: 'El telefono es excelente e incluye Apple Intelligence!',
    price: 1600
}
]

export const ProductsApp = ({ title = 'Title default!' }) => {

    const [products, setProducts] = useState([]);
    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    });

    useEffect(() => {
        setProducts(initProducts);
        console.log('Cargando la pagina ...');
    }, []);

    const handlerAddProduct = (product) => {
        if(product.id > 0){
            setProducts(
                products.map(prod => {
                    if(prod.id == product.id){
                        return {... product};
                    }
                    return prod;
                })
            );
        }else{
            setProducts([...products, { ...product, id: Date.now() }]);
        }
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
        console.log(productSelected)
    }

    const handlerProductRemove = (id) => {
        setProducts(
            products.filter( (product) => {
                return product.id != id
            })
        );
    }

    return <div className='container my-4'>
        <h2>{title}</h2>
        <div className="row">
            <div className='col'>
                <ProductForm handlreAdd={handlerAddProduct} productSelected={productSelected} />
            </div>
            <div className="col">
                {
                    (products.length > 0 )? <ProductTable 
                    products={products} 
                    handlerProductSelected={handlerProductSelected} 
                    handlerProductRemove = {handlerProductRemove} /> : 
                    <div className='alert alert-warning'>No hay productos en el sistema</div>
                }
            </div>
        </div>
    </div>
}

ProductsApp.propTypes = {
    title: PropTypes.string.isRequired
}