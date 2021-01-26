import React from 'react';
import ZirpCard from './ZirpCard';

const ZirpCardList = ({ zirps }) => {
	return (
		<div>
			{ zirps.map(zirp => <ZirpCard key={ zirp.id } zirp={ zirp }></ZirpCard>) }
		</div>
	);
};

export default ZirpCardList;