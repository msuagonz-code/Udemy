import PropTypes from "prop-types";
import { ProductDetail } from "./ProductDetail";

export const ProductGrid = ({handlreRemove, products = []}) => {
    return(
        <table>
            <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>price</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                { 
                    products.map( product => {
                        return <ProductDetail handlreRemove={handlreRemove} product={product} key={product.name}/>
                    }) 
                }
            </tbody>
        </table>
    );
}

ProductGrid.propTypes = {
    products: PropTypes.array.isRequired,
    handlreRemove: PropTypes.func.isRequired
}