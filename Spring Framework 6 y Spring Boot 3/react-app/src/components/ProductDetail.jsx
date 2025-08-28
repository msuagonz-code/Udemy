import PropTypes from "prop-types";

export const ProductDetail = ({ handlreRemove, product = {} }) => {
    return (
                <tr>
                    <td>{product.name}</td>
                    <td>{product.description}</td>
                    <td>{product.price}</td>
                    <td><button onClick={() => handlreRemove(product.name) }>remove</button></td>
                </tr>
    );
}

ProductDetail.propTypes = {
    product : PropTypes.object.isRequired,
    handlreRemove: PropTypes.func.isRequired
}