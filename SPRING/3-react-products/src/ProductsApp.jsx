import { useEffect, useState } from 'react';
import { ProductTable } from './components/ProductTable';
import { ProductForm } from './components/ProductForm';
import { create, findAll, remove, update } from './services/productService';
import PropTypes from "prop-types"
import Swal from 'sweetalert2';

/*
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
*/

export const ProductsApp = ({ title = 'Title default!' }) => {

    const [products, setProducts] = useState([]);
    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    });

    const getProducts = async () =>{

        const result = await findAll();
        setProducts(result.data);
    }

    useEffect(() => {
        getProducts();
        console.log('Cargando la pagina ...');
    }, []);

    const handlerAddProduct = async (product) => {
        if(product.id > 0){
            const response = await update(product);
            setProducts(
                products.map(prod => {
                    if(prod.id == product.id){
                        return { ...response.data };
                    }
                    return prod;
                })
            );
            Swal.fire({
                title: "Actualizado con éxito!",
                text: `Producto ${product.name} actualizado con éxito!`,
                icon: "success"
              });
        }else{
            const response = await create(product);
            Swal.fire({
                title: "Creado con éxito!",
                text: `Producto ${product.name} creado con éxito!`,
                icon: "success"
              });
            setProducts([...products, { ...response.data}]);
        }
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
        console.log(productSelected)
    }

    const handlerProductRemove = (id) => {

        Swal.fire({
            title: "¿Está seguro que desea eliminar?",
            text: "Cuidado!, va a eliminar un producto del sistema",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Sí, Eliminar!",
            cancelButtonText: "No, era bait"
          }).then((result) => {
            if (result.isConfirmed) {
                remove(id);
                setProducts(
                    products.filter( (product) => {
                        return product.id != id
                    })
                );
                Swal.fire({
                    title: "Eliminado con éxito!",
                    text: `Producto eliminado con éxito!`,
                    icon: "success"
                  });
            }
        });

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