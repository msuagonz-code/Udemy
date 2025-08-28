import PropTypes from "prop-types";

export const ProductDetail = ({handlerSelected, handlreRemove, product = {} }) => {
    return (
                <tr>
                    <td>{product.name}</td>
                    <td>{product.description}</td>
                    <td>{product.price}</td>
                    <td><button className="btn btn-primary btn-sm" onClick={() => handlerSelected(product) }>update</button></td>
                    <td><button className="btn btn-danger btn-sm" onClick={() => handlreRemove(product.id) }>remove</button></td>
                </tr>
    );
}

ProductDetail.propTypes = {
    product : PropTypes.object.isRequired,
    handlreRemove: PropTypes.func.isRequired,
    handlerSelected: PropTypes.func.isRequired,
}