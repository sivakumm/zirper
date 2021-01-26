import React from 'react';
import { Jumbotron } from 'reactstrap';

const Header = () => {
    return (
        <div>
            <Jumbotron>
                <h1>
                    Zirper
                    <span className="ml-3"><img src={ process.env.PUBLIC_URL + '/images/cricket.png' } alt="Zirper Logo" height="60px" /></span>
                </h1>
            </Jumbotron>
        </div>
    );
};

export default Header;