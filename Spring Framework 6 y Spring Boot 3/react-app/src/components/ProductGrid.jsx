import PropTypes from "prop-types";
import { ProductDetail } from "./ProductDetail";

export const ProductGrid = ({handlerSelected, handlreRemove, products = []}) => {
    return(
        <table className="table table-hover table-striped">
            <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>price</th>
                    <th>update</th>
                    <th>remove</th>
                </tr>
            </thead>
            <tbody>
                { 
                    products.map( product => {
                        return <ProductDetail 
                        handlerSelected={handlerSelected} 
                        handlreRemove={handlreRemove}
                        product={product} 
                        key={product.name}/>
                    }) 
                }
            </tbody>
        </table>
    );
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlreRemove: PropTypes.func.isRequired,
    handlerSelected: PropTypes.func.isRequired,
}